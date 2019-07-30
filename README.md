# **MTQL5 JAVA autotests**

### The task:

Implement a script to filter events on the economic calendar page.


#### URL:

https://www.mql5.com/en/economic-calendar


#### Technologies \ Tools:

- Java 8

- Wrapper around Selenium WebDriver of your choice

- It will be a plus to use any BDD framework.

- Logging framework of your choice to save logs to file

- Running a script in Google Chrome browser with Googlebot user agent


#### Scenario:

1. Open the Economic Calendar page in the browser.

2. Filter calendar events by the following parameters:

    - Period - Current month

    - Importance - Medium

     - Currency - CHF - Swiss frank

3. Open the first event in the filtered list with CHF currency.

4. Check that the priority and country of the displayed event match the selected filters.

5. Log the history of events in the last 12 months as a table.

    - Example table:

        | Date | Actual | Forecast | Previous |

        | 31 May 2019 | -0.7 & | 0.3% | 0.0% |


#### Additionally :

*  Using the lst.to API (https://lst.to/en/api), shorten the open event link and create a short link together with the received Response DTO to output to the log. After a short link, be sure to delete through the API

* Note: Rest Client should use Googlebot user agent in headers when working with the API.


# **RESULT**

##### **HOW TO RUN**
Execute maven command

```sh
$ mvn clean test
```

##### **HOW TO GET LOGS**
Read the mylogs.log file

```sh
less mylog.log
```  

##### **HOW TO GET HTML REPORT**
Open index.html file that is located target/site/cucumber-pretty

```sh
target/site/cucumber-pretty/index.html
```  

##### **STRUCTURE**

1) The scenarios should be located at the 'test/features' folder
2) Project properties are in the 'resources' folder.
3) Step definition are described at 'java/steps' folder 
4) All action and all elements on Web-page are separated by page and specified at 'java/pages' folder
5) The API rest actions and models for this present at 'java/rest' folder


