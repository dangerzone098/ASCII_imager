## NOTE FOR UML DIAGRAM ##
method and field signatures are public unless written private


for storing the char brightness table, we will use a BST tree,
ordered based on brightness value as a tree
the tree will have key-value pairs:
    - int key = brightness value
    - char value = char itself

this way we get O(log(n)) insertion, removal and search.
which is a good balance between the three.
