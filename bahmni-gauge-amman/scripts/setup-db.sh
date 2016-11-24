#!/usr/bin/env bash


ssh $ENVIRONMENT_USERNAME@$BAHMNI_GAUGE_APP_HOSTNAME "wget https://raw.githubusercontent.com/Bahmni/bahmni-scripts/master/deletePatientData/deletePatientDataForOpenMRS.sql && mysql -u $MYSQL_DB_USERNAME -p$MYSQL_DB_PASSWORD openmrs < deletePatientDataForOpenMRS.sql && rm deletePatientDataForOpenMRS.sql"
