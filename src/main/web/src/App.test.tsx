import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

test('renders the App component with Home route', () => {
  const { getByText } = render(<App suppressContentEditableWarning={true}/>);

  // Überprüfen Sie, ob der Text oder ein anderes Element aus Ihrer Home-Komponente vorhanden ist.
  expect(getByText('DHBW')).toBeInTheDocument();
  expect(getByText('Hub')).toBeInTheDocument();
});
