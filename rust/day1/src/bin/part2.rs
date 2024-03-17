use std::fs;

fn main() {
    let contents: String = fs::read_to_string("input.txt").expect("should have been able to read file");
    let mut chars: Vec<char> = contents.chars().collect(); // split string into vec of chars
    chars.pop(); // remove newline

    let num_count: usize = chars.len();
    let steps: usize = num_count / 2; // how far ahead to look for each pair
    
    chars.extend(chars.clone()); // double up so we can look ahead
    
    let sum: u32 = std::ops::Range{start: 0, end: num_count}.into_iter()
        .filter(|i| chars[*i] == chars[*i+steps]) // only want indexes where both values are the same
        .map(|i| chars[i].to_digit(10).unwrap()) // get char at index and convert to a number
        .sum(); // add up
    
    println!("{}", sum);
}