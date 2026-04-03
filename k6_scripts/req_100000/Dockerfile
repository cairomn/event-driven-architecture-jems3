FROM golang:1.20.10-alpine3.17 as builder

WORKDIR /app

ENV CGO_ENABLED 0
RUN go install go.k6.io/xk6/cmd/xk6@latest

# Your extension's github url, I used mine as example
RUN xk6 build \
    --with github.com/pmalhaire/xk6-mqtt@latest \
    --with github.com/mostafa/xk6-kafka@latest

FROM alpine

COPY --from=builder /app/k6 /bin/
COPY . .

ARG TEST
ENV _HOST_NAME ${TEST}

RUN echo ${_HOST_NAME}
ENTRYPOINT k6 run --summary-export ${_HOST_NAME} ./index.js