#! /bin/bash

#####
# Requests to 100 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST01_REQ8.json docker compose up -d --build
sleep 4m 

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST02_REQ8.json docker compose up -d --build
sleep 120s

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST03_REQ8.json docker compose up -d --build
sleep 120s

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST04_REQ8.json docker compose up -d --build
sleep 120s

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST05_REQ8.json docker compose up -d --build
sleep 120s

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST06_REQ8.json docker compose up -d --build
sleep 120s

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST07_REQ8.json docker compose up -d --build
sleep 120s

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST08_REQ8.json docker compose up -d --build
sleep 120s

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST09_REQ8.json docker compose up -d --build
sleep 120s

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_8
TEST=./outputs/$1_TEST10_REQ8.json docker compose up -d --build
sleep 120s

#####
# Requests to 1000 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST01_REQ32.json docker compose up -d --build
sleep 6m 

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST02_REQ32.json docker compose up -d --build
sleep 3m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST03_REQ32.json docker compose up -d --build
sleep 3m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST04_REQ32.json docker compose up -d --build
sleep 3m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST05_REQ32.json docker compose up -d --build
sleep 3m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST06_REQ32.json docker compose up -d --build
sleep 3m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST07_REQ32.json docker compose up -d --build
sleep 3m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST08_REQ32.json docker compose up -d --build
sleep 3m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST09_REQ32.json docker compose up -d --build
sleep 3m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_32
TEST=./outputs/$1_TEST10_REQ32.json docker compose up -d --build
sleep 3m

#####
# Requests to 10000 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST01_REQ128.json docker compose up -d --build
sleep 7m # Waits 10 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST02_REQ128.json docker compose up -d --build
sleep 4m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST03_REQ128.json docker compose up -d --build
sleep 4m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST04_REQ128.json docker compose up -d --build
sleep 4m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST05_REQ128.json docker compose up -d --build
sleep 4m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST06_REQ128.json docker compose up -d --build
sleep 4m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST07_REQ128.json docker compose up -d --build
sleep 4m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST08_REQ128.json docker compose up -d --build
sleep 4m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST09_REQ128.json docker compose up -d --build
sleep 4m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_128
TEST=./outputs/$1_TEST10_REQ128.json docker compose up -d --build
sleep 4m

#####
# Requests to 50000 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST01_REQ512.json docker compose up -d --build
sleep 8m # Waits 10 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST02_REQ512.json docker compose up -d --build
sleep 5m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST03_REQ512.json docker compose up -d --build
sleep 5m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST04_REQ512.json docker compose up -d --build
sleep 5m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST05_REQ512.json docker compose up -d --build
sleep 5m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST06_REQ512.json docker compose up -d --build
sleep 5m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST07_REQ512.json docker compose up -d --build
sleep 5m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST08_REQ512.json docker compose up -d --build
sleep 5m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST09_REQ512.json docker compose up -d --build
sleep 5m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_512
TEST=./outputs/$1_TEST10_REQ512.json docker compose up -d --build
sleep 5m

#####
# Requests to 100000 linear
#####
cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST01_REQ2048.json docker compose up -d --build
sleep 8m # Waits 10 minutes.

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST02_REQ2048.json docker compose up -d --build
sleep 6m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST03_REQ2048.json docker compose up -d --build
sleep 6m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST04_REQ2048.json docker compose up -d --build
sleep 6m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST05_REQ2048.json docker compose up -d --build
sleep 6m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST06_REQ2048.json docker compose up -d --build
sleep 6m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST07_REQ2048.json docker compose up -d --build
sleep 6m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST08_REQ2048.json docker compose up -d --build
sleep 6m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST09_REQ2048.json docker compose up -d --build
sleep 6m

cd /home/cairo/iot-mestrado/k6_scripts/exp_req_2048
TEST=./outputs/$1_TEST10_REQ2048.json docker compose up -d --build
sleep 6m
