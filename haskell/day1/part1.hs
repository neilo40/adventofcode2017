import Language.Haskell.TH (match)
{-
--- Day 1: Inverse Captcha ---

You're standing in a room with "digitization quarantine" written in LEDs along one wall. The only door is locked, but it includes a small interface. 
"Restricted Area - Strictly No Digitized Users Allowed."

It goes on to explain that you may only leave by solving a captcha to prove you're not a human. Apparently, you only get one millisecond to solve the 
captcha: too fast for a normal human, but it feels like hours to you.

The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list. 
The list is circular, so the digit after the last digit is the first digit in the list.

For example:

    1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
    1111 produces 4 because each digit (all 1) matches the next.
    1234 produces 0 because no digit matches the next.
    91212129 produces 9 because the only digit that matches the next one is the last digit, 9.

-}

main :: IO ()
main = do
  -- a lot of this could be inlined, but I am learning basic syntax here
  content <- readFile "input.txt" -- what is <- ?
  let nums = readInts content
  let pairs = makePairs nums
  let filtered = [x | x <- pairs, matchingPair x]
  let firstNums = [fst x | x <- filtered]
  print $ sum firstNums -- could also write print (sum, firstNums)

-- read converts string or list of char to int
-- [x] turns single char x into list of char/string
-- take x from each elem of chars, except where x = \n
readInts :: String -> [Int]
readInts chars = [read [x] | x <- chars, x /= '\n']

-- zip creates pairs between each elem of each arg (e.g. (arg1[0], arg2[0]) and so on)
-- xs is first arg
-- tail xs is xs[1..end], so zip is joining elem 0 with elem 1 and so on
-- ++ [head xs] adds the first elem of xs to the end of the second list as per the puzzle instructions
makePairs :: [Int] -> [(Int,Int)]
makePairs xs = zip xs (tail xs ++ [head xs])

-- could use fst p == snd p, or head p == last p, but IDE suggested uncurry
matchingPair :: (Int,Int) -> Bool
matchingPair = uncurry (==)
