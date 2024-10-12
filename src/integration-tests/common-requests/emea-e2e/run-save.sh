../../ijhttp/ijhttp \
  ../../Get-HealthCheck.http \
  ../../Get-AllUsers.http \
  ../../Get-UserById.http \
  ../../Get-UsersByFieldLike.http \
  ../../Post-User.http \
  ../../Put-UserById.http \
  ../../Delete-UserById.http \
  --env-file ../../environment/http-client.env.json --env emea-test --insecure --log-level=VERBOSE  --env-variables mode=save
