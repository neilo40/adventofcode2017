use std::fs::read_to_string;

fn main() {
    let mut lines: Vec<Vec<i32>> = read_to_string("input.txt").unwrap().lines()
        .map(|l: &str| l.split_whitespace()
            .map(|e: &str| e.parse::<i32>().unwrap()).collect())
        .collect();

    let checksum: i32 = lines.iter_mut()
        .map(|r: &mut Vec<i32>| {
            r.sort();
            r.last().unwrap() - r.first().unwrap()
        })
        .sum();

    println!("{}", checksum)
}
