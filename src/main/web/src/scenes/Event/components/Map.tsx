import React from 'react';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import {LatLngExpression} from "leaflet";
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';

// @ts-ignore
import icon from 'leaflet/dist/images/marker-icon.png';
// @ts-ignore
import iconShadow from 'leaflet/dist/images/marker-shadow.png';

let DefaultIcon = L.icon({
  iconUrl: icon,
  shadowUrl: iconShadow,
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  shadowSize: [41, 41]
});

L.Marker.prototype.options.icon = DefaultIcon;

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
        <Marker position={position} icon={DefaultIcon}>
          <Popup>
            {address}
          </Popup>
        </Marker>
      </MapContainer>
  );
};