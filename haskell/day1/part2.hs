import Language.Haskell.TH (match)
{-
--- Part Two ---

You notice a progress bar that jumps to 50% completion. Apparently, the door isn't yet satisfied, but it did emit a star as encouragement. The instructions change:

Now, instead of considering the next digit, it wants you to consider the digit halfway around the circular list. That is, if your list contains 10 items, only include a digit in your sum if the digit 10/2 = 5 steps forward matches it. Fortunately, your list has an even number of elements.

For example:

    1212 produces 6: the list contains 4 items, and all four digits match the digit 2 items ahead.
    1221 produces 0, because every comparison is between a 1 and a 2.
    123425 produces 4, because both 2s match each other, but no other digit has a match.
    123123 produces 12.
    12131415 produces 4.

What is the solution to your new captcha?

-}

main :: IO ()
main = do
  content <- readFile "input.txt"
  let filtered = [x | x <- makePairs (readInts content), matchingPair x]
  let firstNums = [fst x | x <- filtered]
  print $ sum firstNums

-- read converts string or list of char to int
-- [x] turns single char x into list of char/string
-- take x from each elem of chars, except where x = \n
readInts :: String -> [Int]
readInts chars = [read [x] | x <- chars, x /= '\n']

-- zip creates pairs between each elem of each arg (e.g. (arg1[0], arg2[0]) and so on)
-- xs is first arg
-- this appends the second half of the list to the first half
-- essentially rotating it, as per the puzzle instructions
makePairs :: [Int] -> [(Int,Int)]
makePairs xs = zip xs (drop (length xs `div` 2) xs ++ take (length xs `div` 2) xs)

-- could use fst p == snd p, or head p == last p, but IDE suggested uncurry
matchingPair :: (Int,Int) -> Bool
matchingPair = uncurry (==)
