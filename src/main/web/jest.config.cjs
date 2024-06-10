module.exports = {
    transform: {
        '^.+\\.tsx?$': 'ts-jest',
        "^.+\\.(js|jsx)$": "babel-jest"
    },
    testEnvironment: "jsdom",
};