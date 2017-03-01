#! /bin/sh

cd /usr/lib/postgresql/9.6/bin/
export PGDATABASE=sonardb
export PGUSER=sonar
export PGPASSWORD=sonar
psql -f "/tmp/postgres_scripts.sql"
