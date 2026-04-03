import { check, fail, sleep } from 'k6';
import http from "k6/http";
import { Trend, Rate } from "k6/metrics";

export let GetExecActionReqs = new Rate('get_exec_action_reqs');
export let GetExecActionDuration = new Trend('get_exec_action_duration');
export let GetExecActionSuccessRate = new Rate('get_exec_action_success_rate');
export let GetExecActionFailRate = new Rate('get_exec_action_fail_rate');

export let MqttNumberOfReadings = new Rate('mqtt_number_of_readings');

// Configuracoes MQTT
const mqtt = require('k6/x/mqtt');

const rnd_count = 2000;
let rnd = Math.random() * rnd_count;
let connectTimeout = 5000 // conection timeout (ms)
let subscribeTimeout = 10000 // subscribe timeout (ms)
let closeTimeout = 5000 // connection close timeout (ms)
const k6Topic = 'lasdpc/status/check'; // Connect IDs one connection per VU
const k6SubId = `k6-pub-${rnd}-${__VU}`

// number of message pusblished and receives at each iteration
//const messageCount = 1;

const host = "10.1.3.71";
const port = "1884";

// Create subscribe client
let subscriber = new mqtt.Client(
  [host + ":" + port], // The list of URL of  MQTT server to connect to
  "", // A username to authenticate to the MQTT server
  "", // Password to match username
  false, // clean session setting
  k6SubId, // Client id for reader
  connectTimeout // timeout in ms
);

let err;

try {
    subscriber.connect()
} catch (error) {
    err = error
}

if (err != undefined) {
    console.error("subscribe connect error:", err)
    // you may want to use fail here if you want only to test successfull connection only
    //fail("fatal could not connect to broker for subscribe")
}

export const options = {
    scenarios: {
        analise_linear_1000: {
            executor: 'constant-arrival-rate',
            rate: 4,
            timeUnit: '1s', // 4 iterations per segundo, i.e. 10 RPS
            duration: '25s', // 100 iterações no total
            preAllocatedVUs: 100, // how large the initial pool of VUs would be
            maxVUs: 200, // if the preAllocatedVUs are not enough, we can initialize more
        },
    },
};

export default function() {
    check(subscriber, {
        "is subcriber connected": subscriber => subscriber.isConnected()
    });

    try {
        subscriber.subscribe(
            k6Topic,
            0,
            subscribeTimeout,
        )
    } catch (error) {
        err = error
    }

    if (err != undefined) {
        console.error("subscribe error:", err)
        // you may want to use fail here if you want only to test successfull connection only
        // fail("fatal could not connect to broker for subscribe")
    }

    subscriber.addEventListener("message", (obj) => {
        // closing as we received one message
        let message = obj.message
        MqttNumberOfReadings.add(1);
        console.error(obj)
    })

    subscriber.addEventListener("error", (err) => {
        MqttNumberOfReadings.add(0);
        console.error(err)
    });

    let requestBody = {
        actuatorID: "8be14deb-a2ea-4b66-b22e-f57a0dcd9c3a",
        microID: "db36ff9d-c182-4681-a931-44243e056fb0",
        valor: 1.0
    };

    let res = http.post(
        'http://andromeda.lasdpc.icmc.usp.br:8010/api/v1/microcontroladores/db36ff9d-c182-4681-a931-44243e056fb0/on-off-air-cond',
        JSON.stringify(requestBody),
        { headers: { 'Content-Type': 'application/json' } }
    );

    GetExecActionReqs.add(1);
    GetExecActionDuration.add(res.timings.duration);
    GetExecActionSuccessRate.add(res.status < 399);
    GetExecActionFailRate.add(res.status == 0 || res.status > 399);

    let durationMsg = 'Max Duration: ${4000/1000}s'

    if (!check(res, { 'max duration': (r) => r.timings.duration < 4000 })) {
        fail(durationMsg);
    }
}

export function teardown() {
    subscriber.close(closeTimeout);
}
