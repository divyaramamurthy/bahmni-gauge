Automation Test Suite using the Gauge Framework
============================================

Installation instructions
--------------------------
* Download Gauge from [here](http://getgauge.io/get-started/).

* Install core plugins using the following commands
    * `gauge --install java`
    * `gauge --install html-report`

Running functional tests
------------------------
* Run the following commands with proper values to setup the required environment variables.
    * `export BAHMNI_GAUGE_APP_URL=<bahmni application url>`
    * `export BAHMNI_GAUGE_APP_USER=<bahmni username>`
    * `export BAHMNI_GAUGE_APP_PASSWORD=<bahmni password>`
    * `export BAHMNI_GAUGE_APP_IMPL_NAME=<bahmni implementation name>`
    * `export BAHMNI_GAUGE_APP_LOCATION=<bahmni login location>`
    * `export BAHMNI_GAUGE_APP_ANOTHER_LOCATION=<bahmni another login location for multiple visits check>`

* Running functional tests
    * Run `mvn clean install` at the root project level.
    * To run functional tests for a specific project, for example the endtb project
        * `cd bahmni-gauge-endtb`
        * `mvn gauge:execute`

Running in docker container (useful for CI)
-------------------------------------------
The run-docker.sh script contains instructions on running the functional tests inside a docker container
* `docker run -v /var/go/.m2:/root/.m2:rw -v $PWD:/gauge -e ENV=$1 -e TAGS=$2 -i bharatak/docker-gauge-chromedriver:chromedriver-2.34 -- sh run.sh`
* It leverages a maven image and sets up chromedriver, Xvfb and Google Chrome
* The run.sh script runs inside the docker container which runs the functional tests

FAQ
--------------------------
* Xvfb gets stopped on reboot of the machine
    * To make it running again, please run the below commands
      * `Xvfb -ac :99 -screen 0 1280x1024x16 &`
      * `export DISPLAY=:99`

* Running Single Spec locally
    * `cd bahmni-gauge-default`
    * `mvn gauge:execute -DspecsDir=specs/Registration.spec`
    * For any changes in the `bahmni-gauge-commons` module create the jar again with `mvn clean install`