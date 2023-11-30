# Countries Application

![Main Screen](https://imgur.com/a/F81aGXy)

# Application Overview:

* The application fetches a list of countries from a remote server using the provided URL.
* The list of countries is displayed in a RecyclerView, with each item showing information such as the country's name, region, code, and capital.
* The application uses clean architecture principles, including the use of coroutines for handling asynchronous operations.
* The code includes the implementation of a ViewModel (CountriesListViewModel) responsible for managing the UI-related data and interactions.
* The GetCountriesUseCase class serves as an intermediary between the presentation layer (ViewModel) and the data layer (repository), handling the business logic for retrieving countries.
* The repository (CountriesRepositoryImpl) interacts with a CountriesApi to fetch country data.
* Unit tests have been implemented for the ViewModel (CountriesListViewModel) and the GetCountriesUseCase, covering scenarios like successful data retrieval and error handling.

# Key Components:

* CountriesListViewModel: Manages the UI-related data and interactions for displaying the list of countries.

* GetCountriesUseCase: Handles the business logic for retrieving the list of countries.

* CountriesRepositoryImpl: Implements the repository interface (CountriesRepository) and interacts with the remote API (CountriesApi) to fetch country data.

* CountriesApi: Represents the remote API for fetching country information.

* CountryDto: Data transfer object representing the structure of country data received from the API.

# Testing:

* Unit tests have been implemented using the MockK library to ensure the correctness of the ViewModel and UseCase classes.

# Technologies/Frameworks:

* Kotlin programming language.
* Coroutines for asynchronous programming.
* Clean architecture principles.
* Retrofit for API communication.
* MockK for mocking in unit tests.
