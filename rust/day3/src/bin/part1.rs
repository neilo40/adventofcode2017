fn main() {
    let input: i32 = 312051;

    // get square of each odd number (n) until value is greater than input
    let mut n: i32 = 3;
    loop {
        n += 1;
        if n % 2 == 0 {
            continue
        }
        if (n * n) > input {
            break
        }
    }

    // input is now on the edge of a square n*n - find its distance from nearest centre
    let edge_length: i32 = ((n * n) - ((n-2) * (n-2))) / 4;
    for i in 0..4 {
        let last_corner: i32 = (n * n) - (i*edge_length);
        if last_corner < input {
            // work out manhattan dist
            println!("{}", last_corner + (edge_length/2) - input + (n-1)/2);
            break
        }
    }

}
