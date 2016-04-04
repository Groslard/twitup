package com.iup.tp.twitup;

import com.iup.tp.twitup.core.Twitup;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitupLauncher
{

  /**
   * Launcher.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    Twitup twitup = new Twitup();
    twitup.initControllers();
    twitup.initGui();
    
  }

}
