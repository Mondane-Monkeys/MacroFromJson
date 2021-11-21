Duplicate Keys:
    The json file will prioritize macros from "macros" over "replaceMacros", and from top to bottom. 
    If two macros have the same key, the one that appears first in the json file will be used.
    Default macros will always take priority.

Allowed characters:
    1. Keys may only contain alphanumeric characters and forward slashes
    2. code may contain any characters
    3. carBack must be an integers and determines the resting place of the caret after the macro is run. 
    4. There is no reasonable limit to the number of characters a code or key may have. 
 
 
Example json layout
-----------
{
    "macros": [
        {
        "key":"Ex",
        "code": ": 0",
        "carBack": 0
        },
        {
        "key":"print",
        "code": "{ln("")}",
        "carBack": 2
        },
    ],
    "replaceMacros": [
        {
            "key":"Ex2",
            "code": "Example 2",
            "carBack": 0
        },
        {
            "key":"//comment",
            "code": "/*\n   \n*/",
            "carBack": 0
        }
    ]
}
-----------
In the example above, 
"Ex" -> "Ex: 0"
"print" -> "println("")" with the caret between the ""
"Ex2" -> "Example 2"
"//comment" -> "  /*
   
 				  */ "
 				  
