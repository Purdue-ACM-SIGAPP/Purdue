# Purdue App

This is the official Purdue Application for Purdue University.
Developed by 2014-2015 Purdue ACM SIGAPP Members and currently available on the
[Play Store here](https://play.google.com/store/apps/details?id=edu.purdue.app).

# Contributing

We follow the Github Flow model of development.
If you would like to contribute to this project and do not have push access, fork it on Github then file a pull request with your changes.
We'd also love if you'd come by SIGAPP meetings every Thursday at 7pm and Saturday at 4pm in LWSN B148!

# Style

*Under Development*

Here is the official list of design guidelines for Purdue: http://www.purdue.edu/marketing/toolkit/design/index.html.
Try to stick to official purdue colors and themes as much as possible!
Here's a quick list of the RGB values for acceptable colors:

    Core Colors:

    Legacy:                             Expanded Core:

    Black 0,0,0                         PMS 7405C (Golden Yellow): 227,174,36

    Old Gold: 163,121,44                PMS 405C (Grey Tan): 116,108,102

                                        40% Black: 167,169,172

                                        20% Black: 209,211,212

                                        White: :)
    Secondary:

    Cools:                              Warms:

    PMS 7498C (Dark Green): 63,75,0     PMS 246C (Magenta): 182,63,151

    PMS 576C (Forest Green): 92,135,39  PMS 7518C (Brown): 126,84,58

    PMS 7473C (Aquamarine): 46,175,164  PMS 1675C (Red): 185,89,21

    PMS 630C (Light Blue): 126,208,224  PMS 144C (Orange): 248,152,29

    PMS 652C (Lavender): 114,153,198    PMS 584C (Pale Yellow):217,218,86

    PMS 7545C (Grey Blue): 92,111,123   PMS 398C (Mustard Yellow): 184,179,8

## Package Names

Classes which might be shared between parts of the app will exist in general packages,
whereas classes which are specific to one part should exist in an appropriately named
package for that part.

For example, a MainMenuAdapter would only be used for the main menu, so it is in the
`mainmenu` package. But a NoInternetDialog might be used in multiple places, so it goes in the
`dialogs` package.

Effort should be made to make your contributions as generic as possible in order to
maximize code reuse and also maintain a consistent visual style throughout
the application.

`utility` is a general package for primarily static methods which might be used anywhere in
 the project.
