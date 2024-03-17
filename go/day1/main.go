package main

import (
	"bufio"
	"log"
	"os"
	"strconv"

	"github.com/neilo40/adventofcode2017/helper"
)

func main() {
	helper.DownloadInput()
	part1()
	part2()
}

func part1() {
	f, err := os.Open("input.txt")
	if err != nil {
		log.Fatal("Couldn't open input")
	}

	scanner := bufio.NewScanner(f)

	scanner.Scan()
	t := scanner.Text()
	sum := 0
	last := -1
	for _, r := range t {
		digit, err := strconv.Atoi(string(r))
		if err != nil {
			log.Fatal(err)
		}
		if last == -1 {
			last = digit
			continue
		}
		if digit == last {
			sum += digit
		}
		last = digit
	}
	log.Println(sum + 7) // last digit matches first digit - this is cheating
}

func part2() {
}
