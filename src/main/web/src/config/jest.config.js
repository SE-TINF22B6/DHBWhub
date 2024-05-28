module.exports = {
    roots: ['<rootDir>'],
    preset: 'ts-jest',
    verbose: true,
    moduleFileExtensions: ["js", "jsx", "ts", "tsx"],
    moduleDirectories: ["node_modules", "src"],
    moduleNameMapper: {
        "\\.(css|less|scss)$": "identity-obj-proxy"
    },
    transform: {
        '^.+\\.ts?$': 'ts-jest',
        "^.+\\.(js|jsx)$": "babel-jest"
    },
    transformIgnorePatterns: ["node_modules/(?!axios)"],
    globals: {
        "ts-jest": {
            isolatedModules: true,
        },
    }
};