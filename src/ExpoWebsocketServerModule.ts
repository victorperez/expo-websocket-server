import { NativeModule, requireNativeModule } from 'expo-modules-core';

import { ExpoWebsocketServerModuleEvents } from './ExpoWebsocketServer.types';

declare class ExpoWebsocketServerModule extends NativeModule<ExpoWebsocketServerModuleEvents> {
  start(port?: number): void;
  stop(): void;
  isRunning(): boolean;
  getLocalIpAddress(): string;
}

// This call loads the native module object from the JSI.
export default requireNativeModule<ExpoWebsocketServerModule>('ExpoWebsocketServer');
