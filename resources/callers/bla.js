a = 1

process.stdin.on('readable', () => {
    let chunk;
    // Use a loop to make sure we read all available data.
    while ((chunk = process.stdin.read()) !== null) {
        process.stdout.write(`data: ${chunk}\n`);
        // process.stdout.write(`data: ${chunk}`);
        console.log("QWEQWEEE" + a++)

        if (a > 10000) process.abort()
    }
});