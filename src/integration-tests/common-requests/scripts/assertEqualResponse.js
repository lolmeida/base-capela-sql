const requestId = client.global.get("requestId");
var ignoreKeys = client.global.get("ignoreKeys");
ignoreKeys = ignoreKeys == null ? [] : JSON.parse(ignoreKeys);
var mode = client.global.get("mode");
mode = mode == null ? request.environment.get("mode") : mode;

function isString(obj) {
    return (typeof obj == 'string' || obj instanceof String);
}

function deepEqual(obj1, obj2, ignoreKeys) {
    if (obj1 == obj2) {
        return true;
    }

    if (typeof obj1 !== 'object' || typeof obj2 !== 'object' || obj1 === null || obj2 === null) {
        return false;
    }

    //Ignore order of arrays
    if (Array.isArray(obj1) && Array.isArray(obj2)) {
        if (obj1.length !== obj2.length) {
            return false;
        }
        return obj1.every(item1 => obj2.some(item2 => deepEqual(item1, item2, ignoreKeys)));
    }

    let keys1 = Object.keys(obj1).filter(key => !ignoreKeys.some(ignoreKey => ignoreKey == key));
    let keys2 = Object.keys(obj2).filter(key => !ignoreKeys.some(ignoreKey => ignoreKey == key));

    if (keys1.length !== keys2.length) {
        return false;
    }

    for (let key of keys1) {
        if (!keys2.includes(key)) {
            return false;
        }
        if (!deepEqual(obj1[key], obj2[key], ignoreKeys)) {
            return false;
        }
    }

    return true;
}

function writeFile(data) {
    let imports = new JavaImporter(java.lang, java.nio.file, java.io);

    data = isString(data) ? data : JSON.stringify(data);

    with(imports) {
        try {
            let tmpDir = System.getProperty("java.io.tmpdir");
            let tmpFile = Paths.get(tmpDir, requestId);

            let writer = new BufferedWriter(new FileWriter(tmpFile));
            writer.write(data);

            writer.close();
            client.log("Saved response file to " + tmpFile.toString())
        } catch (e) {
            client.log(e);
            throw e;
        }
    }
}

function readFile() {
    let imports = new JavaImporter(java.lang, java.nio.file);

    with(imports) {
        try {
            let tmpDir = System.getProperty("java.io.tmpdir");
            let tmpFile = Paths.get(tmpDir, requestId);

            const result = String(Files.readAllBytes(Paths.get(tmpFile)))

            client.log("Read response from " + tmpFile.toString())

            return result;
        } catch (e) {
            client.log(e);
            throw e;
        }
    }
}

if (mode == "save") {
    writeFile(response.body);
} else if (mode == "verify") {
    let savedResponse = readFile();

    if (!isString(response.body)) {
        savedResponse = JSON.parse(savedResponse);
    }

    client.test("New response equals saved response", function() {
        client.assert(deepEqual(savedResponse, response.body, ignoreKeys), `Expected ${savedResponse} got ${response.body}`);
    });
} else {
    throw "Unknown mode: " + mode
}