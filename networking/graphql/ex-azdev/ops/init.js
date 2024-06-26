// DEPLOY_ENV
db.createUser({user: "azdev", pwd: "azdev", roles:[{role: "readWrite", db: "azdev"}]});

db.createCollection("approachDetails", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["pgId"],
      properties: {
        pgId: {
          bsonType: "int",
          description: "must be an integer and is required"
        },
      }
    }
  }
});

db.approachDetails.insertMany([
  {
    pgId: NumberInt(2),
    notes: [
      "This will work if you have staged changes (that you want to keep) or even untracked files. It will only get rid of the unstaged changes.",
    ],
  },
  {
    pgId: NumberInt(3),
    notes: [
      "The `break` statements are needed. Without them, JavaScript will continue to execute all the lines in all the other cases after the one that was matched. That is rarely the intended behavior (although you can use it to define multiple cases that are intended to execute the same code. For example, do something if `expression` equals either `value1` or `value2`)",
    ],
  },
  {
    pgId: NumberInt(4),
    explanations: [
      'Because the function returns for each case, there is no need to "break" out of that case. You can make the function optionally return a value based on the expression as well.',
    ],
  },
  {
    pgId: NumberInt(5),
    explanations: [
      'The `reduce` method invokes its callback function (the first argument) on every item in `arrayOfNumbers`. Each invocation supplies the callback function with an "accumulator" argument and the "current" item for that invocation. What the callback function returns becomes the new value for the accumulator. The initial value of the accumulator is the second argument to `reduce`. By starting with 0 and always returning the sum of the accumulator and the current number in the array, the final result will be the sum of all numbers in `arrayOfNumbers`.',
    ],
  },
  {
    pgId: NumberInt(6),
    notes: [
      "This will only work for Babel versions > 7.x. Older Babels require a different configuration.",
    ],
  },
  {
    pgId: NumberInt(7),
    explanations: [
      'The second argument to hashSync (or hash) is for the "salt" to be used to hash the text. When specified as a number then a salt will be generated with the specified number of rounds and used.',
    ],
    notes: [
      "To do the hashing asynchronously, use the `bycrypt.hash` method. It returns a promise.",
      "To compare hashed texts together, bcrypt has a `compareSync` (and `compare`) methods",
    ],
  },
]);