{
  "patientUuid": "${patient.uuid}",
  "locationUuid": "${patient.locationUuid}",
  "encounterTypeUuid": "81888515-3f10-11e4-adec-0800271c1b75",
  "observations": [
    {
      "concept": {
        "uuid": "84f626d0-3f10-11e4-adec-0800271c1b75",
        "name": "Nutritional Values",
        "dataType": "Numeric"
      },
      "units": null,
      "label": "Nutritional Values",
      "possibleAnswers": [

      ],
      "groupMembers": [
        {
          "concept": {
            "uuid": "5090AAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "name": "HEIGHT",
            "dataType": "Numeric"
          },
          "units": null,
          "label": "HEIGHT",
          "possibleAnswers": [

          ],
          "groupMembers": [

          ],
          "comment": null,
          "isObservation": true,
          "conceptUIConfig": [

          ],
          "uniqueId": "observation_1",
          "erroneousValue": false,
          "value": ${patient.height},
          "autocompleteValue": ${patient.height},
          "isFocused": true,
          "showComment": false,
          "__prevValue": 17,
          "_value": ${patient.height},
          "disabled": false,
          "voided": false
        },
        {
          "concept": {
            "uuid": "5089AAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "name": "WEIGHT",
            "dataType": "Numeric"
          },
          "units": null,
          "label": "WEIGHT",
          "possibleAnswers": [

          ],
          "groupMembers": [

          ],
          "comment": null,
          "isObservation": true,
          "conceptUIConfig": [

          ],
          "uniqueId": "observation_2",
          "erroneousValue": false,
          "value": ${patient.weight},
          "autocompleteValue": ${patient.weight},
          "showComment": false,
          "__prevValue": 6,
          "_value": ${patient.weight},
          "disabled": false,
          "voided": false
        }
      ],
      "comment": null,
      "isObservation": true,
      "conceptUIConfig": [

      ],
      "uniqueId": "observation_3",
      "erroneousValue": null,
      "conceptSetName": "Nutritional Values",
      "voided": false
    },
    {
      "concept": {
        "uuid": "c446af3d-3f10-11e4-adec-0800271c1b75",
        "name": "Fee Information",
        "dataType": "N/A"
      },
      "units": null,
      "label": "Fee Information",
      "possibleAnswers": [

      ],
      "groupMembers": [
        {
          "concept": {
            "uuid": "c366184c-3f10-11e4-adec-0800271c1b75",
            "name": "REGISTRATION FEES",
            "dataType": "Numeric"
          },
          "units": null,
          "label": "REGISTRATION FEES",
          "possibleAnswers": [

          ],
          "groupMembers": [

          ],
          "comment": null,
          "isObservation": true,
          "conceptUIConfig": {
            "required": true,
            "label": "Fee"
          },
          "uniqueId": "observation_4",
          "erroneousValue": false,
          "value": ${patient.registrationFee},
          "autocompleteValue": ${patient.registrationFee},
          "showComment": false,
          "__prevValue": 20,
          "_value": ${patient.registrationFee},
          "disabled": false,
          "voided": false
        }
      ],
      "comment": null,
      "isObservation": true,
      "conceptUIConfig": [

      ],
      "uniqueId": "observation_6",
      "erroneousValue": null,
      "conceptSetName": "Fee Information",
      "voided": false
    }
  ],
  "providers": [
    {
      "uuid": "c1c26908-3f10-11e4-adec-0800271c1b75"
    }
  ]
}