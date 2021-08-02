# Exasol Version Number Provider

[![Build Status](https://github.com/exasol/exasol-version-number-provider/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/exasol-version-number-provider/actions/workflows/ci-build.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aexasol-version-number-provider&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Aexasol-version-number-provider)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aexasol-version-number-provider&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aexasol-version-number-provider)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aexasol-version-number-provider&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aexasol-version-number-provider)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aexasol-version-number-provider&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aexasol-version-number-provider)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aexasol-version-number-provider&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Aexasol-version-number-provider)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aexasol-version-number-provider&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Aexasol-version-number-provider)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aexasol-version-number-provider&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Aexasol-version-number-provider)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aexasol-version-number-provider&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Aexasol-version-number-provider)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aexasol-version-number-provider&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Aexasol-version-number-provider)

This java package gives you access to the latest available Exasol docker-db version number.

## Usage

```java
final ExasolVersionNumberProvider versionNumberProvider=new ExasolVersionNumberProviderFactory().getExasolVersionNumberProvider();
        versionNumberProvider.getLatestReleaseNumber();
```

You can also get the latest version for a specific major or minor release using `getLatestReleaseForMajor(...)` or `getLatestReleaseForMinor(...)`.

## Installation

Click on maven central badge (on the top of this page), select version and copy the dependency declaration for your build system.

## Additional Information

* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)