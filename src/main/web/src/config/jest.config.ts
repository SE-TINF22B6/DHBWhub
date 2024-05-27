export default {
    preset: 'ts-jest',
    testEnvironment: 'node',
    globals: {
        'ts-jest': {
            tsconfig: 'tsconfig.json',
        },
    },
    transform: {
        '^.+\\.(ts|tsx)$': 'ts-jest',
        '^.+\\.(js|jsx)$': 'babel-jest',
    },
    transformIgnorePatterns: [
        '/node_modules/(?!axios)',
    ],
    moduleDirectories: ['node_modules', 'src'],
    moduleNameMapper: {
        '^axios$': require.resolve('axios'),
        '@services/(.*)': '<rootDir>/src/services/$1',
        '@config/(.*)': '<rootDir>/src/config/$1'
    },
};