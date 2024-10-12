#!/bin/bash

ENV="${1:-emea-test}"

ijhttp/ijhttp \
  Get-HealthCheck.http \
  Get-AllUsers.http \
  Get-UserById.http \
  Get-UsersByFieldLike.http \
  Post-User.http \
  Put-UserById.http \
  Delete-UserById.http \
  --env-file environment/http-client.env.json --env "$ENV" --insecure --report
