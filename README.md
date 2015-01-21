
# arangodb-wal-client

ArangoDB Write-Ahead-Log Client in Java

# TODO

* logger-state: unknown `clients` field https://github.com/triAGENS/ArangoDB/issues/1219
* WalEvent: prevent parsing of the `data` field and just use it as-is (saves a bit of cpu/mem)
