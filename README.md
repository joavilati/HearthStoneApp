# Hearthstone APP Project ![Android CI](https://github.com/joavilati/HearthStoneApp/actions/workflows/main.yml/badge.svg?branch=master&event=push)

This project uses the Hearthstone API available on RapidAPI. To ensure the project works correctly, you need to set up some API keys.

## Configuration

1. Go to [https://rapidapi.com/omgvamp/api/hearthstone](https://rapidapi.com/omgvamp/api/hearthstone) and get your API key (`X-RapidAPI-Key`) and the host (`X-RapidAPI-Host`).

2. In your project's `local.properties` file, add the following lines:

Replace `your_key_here` and `your_host_here` with the values you got in step 1.

```properties
xRapidAPIKey=your_key_here
xRapidAPIHost=your_host_here
