client.test("Should return 401 Unauthorized response code", function() {
    const status = response.status;
    client.assert(status === 401, `Expected 401 but got ${status}`);
});

client.test("Response Content-Type is 'application/json'", function() {
    const contentType = response.headers.valueOf("Content-Type");
    client.assert(contentType.includes("application/json"), `Expected 'application/json' but got '${contentType}'`);
});