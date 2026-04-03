#! /bin/bash

#####
# Requests to 100 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST01_REQ100.json docker compose up -d --build
sleep 3m # Waits 10 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST02_REQ100.json docker compose up -d --build
sleep 30s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST03_REQ100.json docker compose up -d --build
sleep 30s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST04_REQ100.json docker compose up -d --build
sleep 30s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST05_REQ100.json docker compose up -d --build
sleep 30s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST06_REQ100.json docker compose up -d --build
sleep 30s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST07_REQ100.json docker compose up -d --build
sleep 30s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST08_REQ100.json docker compose up -d --build
sleep 30s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST09_REQ100.json docker compose up -d --build
sleep 30s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100
TEST=./outputs/$1_TEST10_REQ100.json docker compose up -d --build
sleep 30s # Waits 6 minutes.

#####
# Requests to 1000 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST01_REQ1000.json docker compose up -d --build
sleep 3m # Waits 10 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST02_REQ1000.json docker compose up -d --build
sleep 60s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST03_REQ1000.json docker compose up -d --build
sleep 60s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST04_REQ1000.json docker compose up -d --build
sleep 60s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST05_REQ1000.json docker compose up -d --build
sleep 60s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST06_REQ1000.json docker compose up -d --build
sleep 60s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST07_REQ1000.json docker compose up -d --build
sleep 60s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST08_REQ1000.json docker compose up -d --build
sleep 60s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST09_REQ1000.json docker compose up -d --build
sleep 60s # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_1000
TEST=./outputs/$1_TEST10_REQ1000.json docker compose up -d --build
sleep 60s # Waits 6 minutes.

#####
# Requests to 10000 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST01_REQ1000.json docker compose up -d --build
sleep 8m # Waits 10 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST02_REQ10000.json docker compose up -d --build
sleep 5m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST03_REQ10000.json docker compose up -d --build
sleep 5m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST04_REQ10000.json docker compose up -d --build
sleep 5m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST05_REQ10000.json docker compose up -d --build
sleep 5m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST06_REQ10000.json docker compose up -d --build
sleep 5m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST07_REQ10000.json docker compose up -d --build
sleep 5m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST08_REQ10000.json docker compose up -d --build
sleep 5m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST09_REQ10000.json docker compose up -d --build
sleep 5m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_10000
TEST=./outputs/$1_TEST10_REQ10000.json docker compose up -d --build
sleep 5m # Waits 6 minutes.

#####
# Requests to 50000 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST01_REQ100000.json docker compose up -d --build
sleep 26m # Waits 10 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST02_REQ1000000.json docker compose up -d --build
sleep 23m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST03_REQ1000000.json docker compose up -d --build
sleep 23m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST04_REQ1000000.json docker compose up -d --build
sleep 23m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST05_REQ1000000.json docker compose up -d --build
sleep 23m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST06_REQ1000000.json docker compose up -d --build
sleep 23m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST07_REQ1000000.json docker compose up -d --build
sleep 23m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST08_REQ1000000.json docker compose up -d --build
sleep 23m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST09_REQ1000000.json docker compose up -d --build
sleep 23m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_50000
TEST=./outputs/$1_TEST10_REQ1000000.json docker compose up -d --build
sleep 23m # Waits 6 minutes.

#####
# Requests to 100000 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST01_REQ10000.json docker compose up -d --build
sleep 50m # Waits 10 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST02_REQ100000.json docker compose up -d --build
sleep 45m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST03_REQ100000.json docker compose up -d --build
sleep 45m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST04_REQ100000.json docker compose up -d --build
sleep 45m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST05_REQ100000.json docker compose up -d --build
sleep 45m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST06_REQ100000.json docker compose up -d --build
sleep 45m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST07_REQ100000.json docker compose up -d --build
sleep 45m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST08_REQ100000.json docker compose up -d --build
sleep 45m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST09_REQ100000.json docker compose up -d --build
sleep 45m # Waits 6 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/req_100000
TEST=./outputs/$1_TEST10_REQ100000.json docker compose up -d --build
sleep 45m # Waits 6 minutes.
