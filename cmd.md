#### Delete all failed github workflows
```bash
# List all failed workflows
# gh run list --repo lolmeida/base-capela-sql --status failure

# List all canceled workflows
# gh run list --repo lolmeida/base-capela-sql --status cancelled



# Delete all failed workflows
gh run list --repo lolmeida/base-capela-sql --status failure --json databaseId --jq '.[].databaseId' | xargs -I {} gh run delete {}

# Delete all canceled workflows
gh run list --repo lolmeida/base-capela-sql --status cancelled --json databaseId --jq '.[].databaseId' | xargs -I {} gh run delete {}

# List all  workflows
gh run list --repo lolmeida/base-capela-sql

```



