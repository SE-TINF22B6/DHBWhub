import React from 'react';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import L, {LatLngExpression} from "leaflet";
import 'leaflet/dist/leaflet.css';

interface MapProps {
  position: LatLngExpression;
  address: string;
}

export const Map: React.FC<MapProps> = ({ position, address}) => {
  return (
      <MapContainer center={position} zoom={16} scrollWheelZoom={false} style={{ height: "100%", width: "100%", borderRadius: "10px" }}>
        <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Marker position={position}>
          <Popup>
            {address}
          </Popup>
        </Marker>

      </MapContainer>
  );
};