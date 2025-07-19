import { registerWebModule, NativeModule } from 'expo-modules-core';

import { ExpoWebsocketServerModuleEvents } from './ExpoWebsocketServer.types';

class ExpoWebsocketServerModule extends NativeModule<ExpoWebsocketServerModuleEvents> {}

export default registerWebModule(ExpoWebsocketServerModule, 'ExpoWebsocketServerModule');
