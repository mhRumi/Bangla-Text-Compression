const fs = require("fs");
const zlib = require("zlib");

const READ_FILE_NAME = "compressedData.txt.br";
const WRITE_FILE_NAME = "data.txt";

// Create read and write streams
const readStream = fs.createReadStream(READ_FILE_NAME);
const writeStream = fs.createWriteStream(WRITE_FILE_NAME);

// Create brotli decompress object
const brotli = zlib.createBrotliDecompress();

// Pipe the read and write operations with brotli decompression
const stream = readStream.pipe(brotli).pipe(writeStream);

stream.on("finish", () => {
  console.log("Done decompressing 😎");
});
