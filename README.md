adi.keezl,ptyair

328097001, 215981465

1. UML Design Explanation:

- ascii_art: This is the package
  It has the Shell which acts as main and AsciiArtState,
  which basically holds all the current data (resolution, charset, output mode, etc.).
  For each command, we created a class that operates according to the request.
  all these classes are held in a sub package called commands.
  There's also a CommandFactory that takes the inline string and determines
  which command its supposed to do.
  Shell asks the CommandFactory for the right command object,
  and then calls execute() on it.
  Each command checks if its input
  is okay and updates the AsciiArtState.
  The point of these extra commands mess is to allow
  for generalization of commands. There's no need to touch
  shell to add a command, only the state class and add
  a new Command class.

- image:
  we added a SubImage class that inherites from Image and holds an additional
  brightness field. Because it was easy to save the brightness value this way
  ImagePad does the padding of the image with white pixels so its dimensions are a power of 2,
  and ImageSplitter does the splitting into subImages based on its resolution field.
  ImageProcessor holds an ImagePad and ImageSplitter instance.

- image_char_matching: Contains the logic for finding the best ASCII character.
  SubImgCharMatcher does the heavy lifting of matching the
  image brightness to the right character,
  using a couple of data structures to cache things and
  speed up the process (more on that below).

2. Data Structures Used:

- TreeSet<Character> (in AsciiArtState): We used this to store the current charset.
  TreeSet keeps the characters sorted by their ascii value so we
  get log(n) across the board.
  This makes the "chars" command just a basic iteration

- for storing the char brightness table, we used a BST tree,
  ordered based on brightness value as a tree
  the tree will have key-value pairs:
  - int key = brightness value
  - char value = char itself

  this way we get O(log(n)) insertion, removal and search.
  which is a good balance between the three.
  this is the data structured that was ussed to hold
  the normalized values.


- but we also needed to store the chars in a seperate
  set that wasn't normalized for when we added or removed chars.
  for that we used a basic map that stores the raw brightness
  of every char we calculated so far.
  This way, if a user adds a character, removes it, and adds it back,
  we don't have to call convertToBoolArray() again.
  It gives O(1) lookup time and satisfies requirement 1.5.2.

- HashMap<String, ICommand> (in CommandFactory): map to link command strings
  to their actual command objects. It makes looking
  up commands in the Shell O(1)

- SubImage[][] cache (cachedSubImages in AsciiArtState): We save the 2D array
  of sub-images here. If the user runs the algorithm multiple
  times without changing the resolution or the image,
  we just reuse this cached array. This saves us from having
  to split the image and calculate the brightness of every block all
  over again (efficiency requirement 1.5.1).

3. Exception Handling:

The Shell run() loop catche exceptions and prints the error message.

We created custom exceptions in the ascii_art.errors package,
all inheriting from the standard Exception class:
- AddCommandException
- RemoveCommandException
- ResolutionCommandException
- ExceededBoundariesException
- OutputCommandFormatException
- UndersizedCharsetException

Each exception is thrown with the exact error message requested in the instructions.
For instance, if the user types something like "res up down",
ResCommand throws a ResolutionCommandException, and the Shell
catches it and prints "Did not change resolution due to incorrect format."
For completely invalid commands that don't match anything,
the Shell just prints the generic error directly.

4. Changes to SubImgCharMatcher API:

We didn't change the required public API at all.
The constructor, getCharByImageBrightness, addChar,
and removeChar are all exactly as specified.

Under the hood, we added the HashMap and TreeMap mentioned
above to handle the caching requirements efficiently.
We also added a private rebuildNormalizedTree() method that
recalculates the normalized values and populates the TreeMap,
but only when necessary.

5. Changes to Supplied Code:

- Image.java: We added one public method, getPixelArray(),
  which returns a copy of the pixelArray.
  We needed this so ImageSplitter could use System.arraycopy
  to split the image quickly, rather than having to call
  getPixel() in a nested loop for every single pixel.
  the rest of the original API is untouced.

