# Security Policy

## Reporting a Security Issue

Please do not publish passwords, access tokens, API keys, database
credentials or other sensitive information in issues or pull requests.

For a suspected security issue, contact the repository owner privately
through the contact information available on the owner's GitHub profile.

## Sensitive Configuration

Local database credentials and environment-specific secrets should remain
outside version control.

Use the example configuration supplied in:

`src/main/resources/application-example.properties.txt`

as a template for local development.

## Public Repository Guidelines

Do not commit:

- Passwords
- API keys
- Authentication tokens
- Private credentials
- Production database connection secrets
- Private customer or user information
