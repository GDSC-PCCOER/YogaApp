# Android-Study-Jams

Yoga App

<b> Problem Statement: </b>
How might we improve the daily yoga app for users so that they can know more about the different Yogasanas and how to efficiently achieve their fitness goal.

<b> Proposed Solution : </b>
Developing a app which displays all the Yogasanas according to the category which will help them to choose the right Yogasanas to practice for themselves.

| | | | |
|:----:|:----:|:----:|:----:|
| <img alt="SS_1" src="https://user-images.githubusercontent.com/36833286/148687732-2c04e3dd-2b6d-4ae2-8450-457e5445b38a.png"> | <img alt="SS_2" src="https://user-images.githubusercontent.com/36833286/148687766-9e480e5a-277b-43f6-a4a2-f1c917b4cde7.png"> | <img alt="SS_3" src="https://user-images.githubusercontent.com/36833286/148687796-e07abb1e-8f7b-481c-92a5-9009f94fa679.png"> | <img alt="SS_4" src="https://user-images.githubusercontent.com/36833286/148687874-18f51154-63aa-41df-ba70-2bfb11367ede.png"> |

    	  	
<b> Functionality & Concepts used : </b>

- Constraint Layout : Most of the fragments in the app uses a flexible constraint layout, which is easy to handle for different screen sizes.
- Simple & Easy Views Design : Simple Card Views used to enable easy understanding of th UI.
- RecyclerView : To present the list of different categories and poses we used the efficient recyclerview.
- Retrofit : We used retrofit library for easy network calls to the Yoga API and for fetching data.
- Room Database : We used ROOM Database for caching of data received from the network for cases where network is not available so that we can display the Yogasanas even when the user is not connected to the Internet.
- LiveData : We used LiveData to inform the UI and Views that data is changed and is available to be displayed.

<b> Application Link & Future Scope : </b>

You can access the app : https://github.com/GDSC-PCCOER/YogaApp/releases/download/1/app-release.apk
