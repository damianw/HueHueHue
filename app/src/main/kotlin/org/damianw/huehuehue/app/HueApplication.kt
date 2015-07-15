package org.damianw.huehuehue.app

import android.app.Application
import com.facebook.stetho.Stetho
import org.damianw.huehuehue.util.DEBUG

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class HueApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    if (DEBUG) {
      Stetho.initialize(
          Stetho.newInitializerBuilder(this)
              .enableDumpapp(
                  Stetho.defaultDumperPluginsProvider(this))
              .enableWebKitInspector(
                  Stetho.defaultInspectorModulesProvider(this))
              .build())
    };
  }
}