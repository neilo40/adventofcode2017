use std::fs;

fn main() {
    let contents: String = fs::read_to_string("input.txt").expect("should have been able to read file");
    let mut chars: Vec<char> = contents.chars().collect(); // split string into vec of chars
    chars.pop(); // remove newline
    chars.push(chars[0]); // add first char to end for wraparound

    let sum: u32 = chars.windows(2) // get sliding window of pairs
        .filter(|e| e[0]==e[1]) // filter for only pairs where both elems are the same
        .map(|e| e[0].to_digit(10).unwrap()) // convert first digit to a number
        .sum(); // add them all together
    
    println!("{}", sum);
}