package com.github.bilak.usercreator.service;

import com.github.bilak.usercreator.data.model.Command;

/**
 * Handler for use commands.
 */
public interface UserCommandHandler extends Runnable {

  /**
   * Handles all commands based on marker interface {@link Command}.
   *
   * @param command the command
   */
  void handleCommand(Command command);

  /**
   * Stops the handler.
   */
  void stop();

}
