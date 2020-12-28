SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[TourGroups]
(
    [TourGroupId] [int] IDENTITY (1,1) NOT NULL,
    [Name]        [varchar](50)        NOT NULL,
    [Description] [varchar](300)       NULL,
    [TourId]      [int]                NOT NULL,
    [MaxAmount]   [int]                NOT NULL,
    CONSTRAINT [PK_TourGroups] PRIMARY KEY CLUSTERED
        (
         [TourGroupId] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
ALTER TABLE [dbo].[TourGroups]
    WITH CHECK ADD CONSTRAINT [FK_TourGroups_Tours1] FOREIGN KEY ([TourId])
        REFERENCES [dbo].[Tours] ([TourId])
        ON DELETE CASCADE
ALTER TABLE [dbo].[TourGroups]
    CHECK CONSTRAINT [FK_TourGroups_Tours1];
