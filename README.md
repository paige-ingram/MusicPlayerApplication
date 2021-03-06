# My Personal Project

## Paige's Playlist

An example playlist:

####Playlist: Dance Yourself Clean from COVID-19

"Like a G6" by Far East Movement

"Motley Crew" by Post Malone

"Low" by Flo Rida

"Ransom" by Lil Tecca

###What will the application do?
This application will store songs in user-generated, named playlists. 
Users can add or remove songs to their playlists.
Users can view all songs located in their playlists.

###Who will use it?
This application will be used by music listeners who want to organize their music. 
Listening to music is a hobby that many of us have in common and can help people de-stress.
A playlist organizer is essential for music listeners who listen to music while doing other tasks.
This program will be especially helpful for people such as athletes warming up before a big game, 
hosts at a big party, and students commuting to school. 
In all of these situations, the person listening to the music is looking for a curated selection 
of their favourite songs, but they do not have the ability to select songs one-by-one at the moment
because they are preoccupied doing something else. 
This is why the music player application will be so useful for users in organizing and categorising 
their songs based on their mood and interests.

###Why is this project of interest to you?
As an avid music listener, this project is of great interest to me, so I can create and manage my music
depending on my current mood. I can keep track of all my favourite songs so when I am on aux at a party 
it will be easy to keep the crowd entertained without constantly thinking about what to play next.

##User Stories

- As a user, I want to be able to create a new playlist.
- As a user, I want to be able to add a song with a specified name and artist to my specified playlist.
- As a user, I want to be able to remove a song from my specified playlist.
- As a user, I want to be able to view all my playlists and view all my songs in a given playlist.
- As a user, I want to be able to save my playlists to a file.
- As a user, I want to be able to be able to load my playlists from a file. 

###Phase 4: Task 2

Thu Nov 25 16:02:42 PST 2021
New playlist, Thursday Vibes, created.


Thu Nov 25 16:02:42 PST 2021
New song 'CLC Study' added to a playlist.


Thu Nov 25 16:02:42 PST 2021
New playlist, Thursday Vibes, added to music player.


Thu Nov 25 16:03:13 PST 2021
New song 'Shannon and Sue' added to a playlist.


Thu Nov 25 16:03:22 PST 2021
Removed song 'CLC Study' from a playlist.

###Phase 4: Task 3

The GUI and MusicPlayerApp classes are both doing essentially the same thing except the GUI is displaying 
the user interface via JFrame windows and the MusicPlayerApp is displaying the user interface via the console.
As my project is currently configured, it does not make any use of the MusicPlayerApp class and does not allow
users to interact with the Music Player through the console. However, if I were to allow users to choose between
displaying the ui in either the console or the JFrame windows, I could:
- create an abstract class for the ui
- the abstract class would have associations with the MusicPlayer, JSONWriter, JSONReader, and LogPrinter classes
- the abstract class would implement methods that are common across both the MusicPlayerApp and GUI classes
- the MusicPlayerApp class would extend the abstract ui class and implement class-specific method functionality
- the GUI class would extend the abstract ui class and implement class-specific method functionality
- I would design a method that allows the user to choose which ui they want to interact with the Music Player
  (as demonstrated in the AlarmSystem PrintLogAction class in the actionPerformed(ActionEvent evt) method)








