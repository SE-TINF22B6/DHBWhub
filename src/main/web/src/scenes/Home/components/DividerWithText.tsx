import * as React from 'react';
import Divider from '@mui/material/Divider';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

const borderStyle = {
    backgroundColor: '#CCCCCC'
};

const boxStyle = {
    border: '1px solid #CCCCCC',
    backgroundColor: '#FFFFFF',
    color: '#5F5F5F',
};

export default function DividerWithText() {
    return (
        <Box display="flex" alignItems="center">
            <Box flex={1} sx={borderStyle}>
                <Divider />
            </Box>
            <Box px={2} border={1} borderColor="divider" borderRadius={1} p={1} sx={boxStyle}>
                <Typography variant="body1">OR</Typography>
            </Box>
            <Box flex={1} sx={borderStyle}>
                <Divider />
            </Box>
        </Box>
    );
}


