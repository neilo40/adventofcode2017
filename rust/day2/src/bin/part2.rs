use std::fs::read_to_string;
use itertools::Itertools;

fn main() {
    let lines: Vec<Vec<i32>> = read_to_string("input.txt").unwrap().lines()
        .map(|l: &str| l.split_whitespace()
            .map(|e: &str| e.parse::<i32>().unwrap()).collect())
        .collect();

    let results: Vec<i32> = lines.iter()
        .map(|l: &Vec<i32>| l.iter()
            .combinations(2)
            // combinations doesn't do ordering, so this nastiness is required to check remainder of dividing both ways
            // could maybe do this by sorting each pair to ensure the numerator is always larger?
            .filter(|p: &Vec<&i32>| (p[0] % p[1] == 0) || (p[1] % p[0] == 0))
            .map(|p: Vec<&i32>| {
                let remainder = p[0]%p[1];
                let result: i32;
                if remainder == 0 {
                    result = p[0] / p[1];
                } else {
                    result = p[1] / p[0];
                }
                result
            })
            .sum::<i32>())
        .collect();     

    println!("{}",  results.iter().sum::<i32>())
}
