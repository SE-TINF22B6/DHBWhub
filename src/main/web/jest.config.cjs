module.exports = {
    transform: {
        '^.+\\.ts?$': 'ts-jest',
        "^.+\\.(js|jsx)$": "babel-jest"
    },
    moduleNameMapper: {
          "^axios$": "axios/dist/node/axios.cjs"
    }
};
