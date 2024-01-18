# #Jukebox

## ##Project Structure

- **appConfig:** Contains application-wide configuration files or classes.
- **commands:** Includes modules for processing user commands.
- **entities:**

  - **Playlist:** Represents a collection of songs.
  - **Song:** Represents a musical composition.
  - **User:** Represents a user of the Jukebox system.
- **exceptions:** Custom exception classes for handling specific errors.
- **repositories:** Handles interactions with data storage, such as loading song data from a CSV file.
- **services:** Contains service classes for business logic, e.g., managing playlists.

## ##Key Components

1. **songs.csv:**

   - CSV file containing initial song data.
2. **jukebox-input.txt:**

   - Text file with sample commands for the Jukebox application.
3. **App.java:**

   - Main entry point of the application.
4. **AppTest.java:**

   - Integration test class for overall application flow.
5. **build.gradle:**

   - Build configuration file with instructions for running the Jukebox application.

Feel free to create playlists, add songs, and explore the Jukebox functionalities!
