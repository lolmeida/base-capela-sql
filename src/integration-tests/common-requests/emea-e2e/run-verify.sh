../../ijhttp/ijhttp \
  Get-CargoById-1.http \
  Get-CargoById-2.http \
  --env-file ../../environment/http-client.env.json --env emea-e2e --insecure --log-level=VERBOSE  --env-variables mode=verify
