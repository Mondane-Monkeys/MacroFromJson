Code based on Jonathan Dahlberg's Extended Code Completion 
http://jonathan.dahlberg.media/ecc/

Adapted by Ness Tran
Designed and tested for Processing 3.5.4

How to use:
    The tool will auto complete code by pressing ctlr+spacebar while the caret it at the end of a defined keywords.
    The 15 macros created in the original tool and documented in the above link are enabled by default.
    Custom macros can be added to the functionality by modifying the CustomMacros.json file.

Install instructions:
    1) Extract AutoFromJson.jar to \processing-3.5.4\Tools
    2) Copy the CustomMacros.json file to \processing-3.5.4\config\
    3) For each instance of Processing run, in the toolbar select Tools>AutoFromJson
    4) If the console displays "AutoFromJson is running", the default macros are now active
    5) If the console displays "Success: Json file found at..." then any macros defined in the json file are active
    6) To add more macros, edit the json file at \processing-3.5.4\config\
    
If you encounter any errors or issues, please let me know.    