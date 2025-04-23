#!/bin/sh
echo "Creating MongoDB user"
mongod --fork --logpath /var/log/mongodb/init.log
sleep 5
mongo --quiet admin <<-EOJS
    db.createUser({
        user: "admin",
        pwd: "admin",
        roles: [ { role: 'root', db: "admin" } ]
    })
EOJS
mongod --shutdown
