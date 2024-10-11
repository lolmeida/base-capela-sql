client.global.set("themeVersioned", JSON.stringify(response.body));
const getTheme = (theme) => JSON.parse(client.global.get(theme));
const getAttribute = (theme, attribute) => getTheme(theme)[0].metadata[attribute];

client.test("Should return 200 response code", function () {
    const status = response.status;
    client.assert(status === 200, `Expected 200 but got ${status}`);
});

client.test("Response Content-Type is 'application/json'", function () {
    const contentType = response.headers.valueOf("Content-Type");
    client.assert(contentType.includes("application/json"),
        `Expected 'application/json' but got '${contentType}'`);
});

client.test("Admin info should not be returned", function () {
    client.assert(response.body[0].adminInfo == null,
        "Admin info should not be returned");
});

client.test("Should return correct metadata defaultName", function () {
    const defaultName = getAttribute("themeOriginal", "defaultName");
    client.assert(defaultName === "Untold", `Expected 'Untold' but got ${defaultName}`);
});

client.test("Should return correct metadata defaultName", function () {
    const defaultName = response.body[0].metadata.defaultName;
    client.assert(defaultName === "Untold V2", `Expected 'Untold V2' but got ${defaultName}`);
});