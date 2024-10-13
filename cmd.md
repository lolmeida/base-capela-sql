#### Delete all failed github workflows
```bash
clear
echo "Delete all FAILED workflows"
gh run list --repo lolmeida/base-capela-sql --status failure --json databaseId --jq '.[].databaseId' | xargs -I {} gh run delete {}

echo "Delete all CANCELLED workflows"
gh run list --repo lolmeida/base-capela-sql --status cancelled --json databaseId --jq '.[].databaseId' | xargs -I {} gh run delete {}
```

