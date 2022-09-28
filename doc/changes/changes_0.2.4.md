# exasol-version-number-provider 0.2.4, released 2022-09-28

Code name: Fix Dockerhub access

## Summary

In this release we fixed the access to the Dockerhub API. Docker removed the deprecated `v1` API so we updated to `v2`. Thanks to [@exaSR](https://github.com/exaSR) for reporting this!

Please note that the new Dockerhub API requires sending multiple requests in order to retrieve all tags. That's why creating a new `ExasolVersionNumberProvider` with `ExasolVersionNumberProviderFactory.buildExasolVersionNumberProvider()` takes a bit longer now.

## Features

* #13: Fixed access to Dockerhub API

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.1` to `1.0.0`
* Added `jakarta.json.bind:jakarta.json.bind-api:3.0.0`

### Runtime Dependency Updates

* Added `org.eclipse:yasson:3.0.2`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-engine:5.9.0` to `5.9.1`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.0` to `5.9.1`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.6.2` to `2.8.0`
